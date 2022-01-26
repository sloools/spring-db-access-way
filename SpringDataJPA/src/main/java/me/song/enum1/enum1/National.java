package me.song.enum1.enum1;

public enum National implements EnumMapper{

    KOREA("대한민국"),
    CANADA("캐나다");

    private final String national;

    National(String na){
        this.national = na;
    }

    @Override
    public String getCode() {
        System.out.println("I am National Enum");
        return national;
    }

    @Override
    public String getTitle() {
        return name();
    }
}
