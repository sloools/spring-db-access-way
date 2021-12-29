package me.song.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 트랜젝션 : 데이터베이스의 상태를 변경하는 작업으로 데이터베이스에서 한 번에 수행되어야 하는 연산들을 의미 (데이터에 대한 하나의 논리적 실행단계)
 *
 * 트랜젝션의 기능
 * 1. begin, commit의 자동 수행
 * 2. 예외 발생하면 rollback 자동 수행
 *
 * 트랜젝션은 데이터베이스의 ACID를 위해 사용된다.
 * A(Atomicity)   원자성 : 한 트랜젝션 내에 실행되는 작업을 하나의 단위로 처리한다 (모두 성공 or 모두 실패)
 * C(Consistency) 일관성 : 트렌젝션은 실행이 성공적으로 완료되면 언제나 데이터베이스를 일관성 있는 상태로 유지한다
 * I(Isolation)   격리성 : 동시에 실행되는 트랜젝션은 서로 영향을 미치지 않도록 상호 독립적으로 격리해야 한다
 * D(Durability)  영속성 : 트랜젝션을 성공적으로 마치면 결과가 영원이 반영되어야 한다. 전형적으로 모든 트랜잭션은 로그로 남고 시스템 장애 발생 전 상태로 되돌릴 수 있다. 트랜잭션은 로그에 모든 것이 저장된 후에만 commit 상태로 간주될 수 있다.
 *
 * Spring Transaction의 3가지 기술
 * 1. 동기화 : 트랜젝션을 위한 Connection 객체를 저장소에 보관하고 필요할 때 꺼내 쓸 수 있도록 하는 기술. JDBC는 쓰레드마다 Connection 객체를 독립적으로 관리한다.
 *            하지만 Hibernate에서는 Connection이 아닌 Session 객체를 사용하기 때문에 기술 종속적인 문제가 발생한다.
 * 2. 추상화 : 동기화에서 발생하는 기술 종속적 문제를 해결하기 위해 Spring은 PlatformTransactionManager라는 추상화 인터페이스를 제공한다.
 *            어떤 기술을 사용하든(JDBC, JPA, Hibernate 등) 일관되게 트랜젝션을 처리할 수 있다.
 * 3. AOP를 이용한 트랜젝션 분리 :
 *            트랜젝션 관리 코드와 비지니스 로직을 분리하기 위해 Spring은 AOP를 적용한 트랜젝션 어노테이션 @Transactional을 제공한다.
 *            Spring AOP는 Dynamic Proxy 방식이 Default이므로 인터페이스를 가지고 있어야 한다. 인터페이스를 가지지 않고 트랜젝션을 사용하려면 CGLib Proxy 옵션을 설정해주면 된다.
 *
 *
 *
 * @Transactional 어노테이션은 Spring AOP 방식을 이용한다 (Spring AOP는 디폴트로 Dynamic Proxy를 이용한다)
 * (Dynamic Proxy는 인터페이스를 기반으로 동작하기 때문에 인터페이스가 없는 경우 트랜젝션이 동작하지 않는다.)
 *
 * @Transactional 옵션
 *
 * 1. Isolation : 격리 수준. 데이터의 일관성을 허용하는 수준을 말한다.
 *                서버에서 여러 개의 트랜젝션을 처리하는 데 적적하게 격리 수준을 조정하여 최대한 많은 트랜젝션을 동시에 진행하면서 문제가 일어나지 않도록 제어해야 한다.
 *                격리 수준이 높을 수록 격리성은 높아지지만 성능은 저하된다.
 *                기본적으로 Default 격리 수준을 따르는 것이 좋으나, 특별한 작업을 수행하는 메소드는 개별 설정이 필요하다.
 *
 *     - Isolation.DEFAULT : RDBMS나 Datasource 의 isolation 레벨을 따른다. (MYSQL : REPEATABLE READ, ORACLE : READ COMMITTED)
 *     - Isolation.READ_UNCOMMITTED (Level 0) : 커밋되지 않은 데이터 혹은 트랜젝션 처리중인 데이터를 다른 트랜젝션이 읽는 것을 허용한다
 *                                              (dirty read, non-repeatable read, Phantom Read 발생)
 *     - Isolation.READ_COMMITTED (Level 1) : 커밋되지 않은 변경사항을 다른 트랜젝션이 읽지 못하게 한다. 커밋 완료된 데이터만 다른 트랜젝션이 읽을 수 있다.
 *                                            (non-repeatable read, Phantom Read 발생)
 *     - Isolation.REPEATABLE_READ (Level 2) : 한 트랜젝션이 read한 데이터는 트랜젝션이 종료될 때까지 다른 트랜젝션에 의해 데이터가 변경될 수 없다. 같은 데이터를 반복하여 읽더라도 일관된 결과를 리턴한다.
 *                                             (Phantom Read 발생)
 *     - Isolation.SERIALIZABLE (Level 3) : 가장 높은 수준의 고립. 데이터의 일관성 및 동시성을 위해서 MVCC(Multi Version Concurrency Control - 다중 사용자의 데이터 조회시 Lock을 걸지 않고 데이터의 버전을 관리하여 일관성 및 동시성을 높이는 기술)을 사용하지 않는다.
 *                                          트랜젝션이 완료될 때까지 SELECT 문장이 사용하는 모든 데이터에 Shared Lock을 걸어 수정과 입력을 불가능하게 만들어 Phantom Read를 방지한다.
 *     ※ dirty read - 작업이 완료되지 않은 데이터를 다른 트랜젝션이 읽는 것. 즉, 업데이트 되지 않은 잘못된 데이터를 읽어오는 것
 *     ※ non-repeatable read - 한 트랜젝션이 쿼리를 조회한 이후에 다른 트랜젝션이 해당 정보를 수정했을 때, 조회했던 트랜젝션이 다시 쿼리를 조회하는 경우 첫 번째와 다른 값이 조회되는 경우. 반복해서 같은 데이터를 읽을 수 없는 경우를 말한다.
 *     ※ phantom read  - 한 트랜젝션 내 같은 쿼리 두 번 실행시 where 절에 조건에 맞는 레코드가 추가로 생성되어 두 번째 쿼리에서 새로운 레코드가 생기는 현상
 *                        예를 들어, SELECT * FROM WHERE 2 < id and id < 5 를 조회하는 트랜젝션이 있을 때, 2 < id and id < 5 라는 조건에 lock을 건다. 이 상황에서 다른 트랜젝션이 id = 3조건으로 레코드를 변경하면 lock 걸린 조건과는 다르다고 인지하여 업데이트가 수행된다.
 *                        하지만 첫 번째 트랜젝션이 다시 같은 쿼리를 조회했을 때 새로운 레코드가 생성된다. 이를 유령 레코드라고 부르며 이와 같은 현상을 phantom read라고 한다.
 *
 * 2. propagation : 한 트랜젝션이 동작할 때 다른 트랜젝션이 호출되면 어떻게 설정할 것인가 대한 옵션
 *     - Propagation.REQUIRED (DEFAULT) : 부모 트랜젝션, 해당 메소드를 호출한 트랜젝션이 있다면 해당 트랜젝션 속성을 따르고, 없으면 트랜젝션을 새로 만든다.
 *     - Propagation.SUPPORTS : 부모 트랜젝션이나, 해당 메소드를 호출한 트랜젝션이 있으면 따르고, 없다면 트랜젝션이 없는 채(non-transactionally)로 동작한다.
 *                              Propagation.SUPPORTS 트랜젝션 내부에서 Propagation.REQUIRED 이나 Propagation.REQUIRED_NEW 가 존재하면 런타임시 동기화 충돌이 날 수 있다.
 *                              예를 들어, 트랜젝션이 없는 채로 SUPPORTS 옵션을 가진 메소드가 REQUIRED 옵션을 가진 메소드를 호출했을 때 내부에서 에러가 나서 롤백을 해야 하는 상황이라면?
 *                              호출된 메소드만 롤백하고 SUPPORT 메소드는 롤백하지 않을 것인가?
 *     - Propagation.MANDATORY : 트랜젝션이 존재하지 않으면 exception을 발생한다. 트랜젝션을 발생하는 메소드의 호출에 의해서만 실행된다.
 *     - Propagation.REQUIRED_NEW : 항상 현재 트랜젝션을 중단하고 새로운 트랜젝션을 생성한다. 이미 진행중인 트랜젝션이 있으면 잠깐 보류하고 해당 트랜젝션 작업을 먼저 실행한다.
 *     - Propagation.NOT_SUPPORTED : 현재 트랜젝션을 보류하고 항상 트랜젝션이 없는 채(non-transactionally)로 동작한다.
 *     - Propagation.NEVER : 트랜젝션이 있으면 exception을 발생하고, 없다면 없는 채(non-transactionally)로 동작한다.
 *     - Propagation.PROPAGATION_NESTED : 현재 트랜젝션이 있다면 중첩하여 트랜젝션을 실행하고(트랜젝션 in 트랜젝션), 없다면 REQUIRED와 동일하게 동작한다(새로운 트랜젝션 생성).
 * 3. rollbackFor : 특정 예외 발생시 강제로 롤백
 *                  Spring boot에서 @Transactional은 디폴트로 런타임 시에 발생되는 exception(Unchecked Exception)에서만 동작한다. => @Retention(RetentionPolicy.RUNTIME)
 *                  이론적으로는 RetentionPolicy를 조정하여 ClassNotFoundException과 같은 컴파일 단계에서 발생하는 Checked Exception에서 동작하게 할 수는 있다. (rollbackFor = Exception.class)
 *
 * 4. noRollbackFor : 특정 예외 발생시 롤백하지 않음
 * 5. timeout : 지정 시간 내에 메소드 수행이 완료되지 않는 경우 롤백 수행. default : -1 (no time out)
 * 6. readOnly : readOnly = true로 설정시 CUD 실행시 Exception 발생 (default : false)
 */

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

}
