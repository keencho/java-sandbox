package sycho.spring.basic.model;

public enum ProgrammingLanguage {
    C("C"),
    JAVA("자바"),
    PYTHON("파이썬"),
    JAVASCRIPT("자바스크립트");

    private final String kor;

    public String getKor() {
        return kor;
    }

    ProgrammingLanguage(String kor) {
        this.kor = kor;
    }
}
