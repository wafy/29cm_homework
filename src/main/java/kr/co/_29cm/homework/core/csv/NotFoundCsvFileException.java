package kr.co._29cm.homework.core.csv;

public class NotFoundCsvFileException extends RuntimeException {

    public NotFoundCsvFileException(String message) {
        super(message);
    }
}
