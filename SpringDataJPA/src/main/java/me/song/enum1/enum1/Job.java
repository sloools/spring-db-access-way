package me.song.enum1.enum1;

public enum Job implements EnumMapper{

    PROGRAMMER("개발자"),
    MD("엠디"),
    PD("피디");

    private final String job;

    Job(String job){
        this.job = job;
    }

    @Override
    public String getCode() {
        System.out.println("I am JOB Enum");
        return job;
    }

    @Override
    public String getTitle() {
        return name();
    }
}