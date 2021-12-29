package me.song.enum1.enum1;

public class EnumMapperValue {
    private String code;
    private String title;

    public EnumMapperValue(EnumMapper enumMapper){
        code = enumMapper.getCode();
        title = enumMapper.getTitle();
    }
}
