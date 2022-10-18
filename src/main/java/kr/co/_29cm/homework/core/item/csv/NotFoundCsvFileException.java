package kr.co._29cm.homework.core.item.csv;

public class NotFoundCsvFileException extends RuntimeException {

    public NotFoundCsvFileException(String message) {
        super(message);
    }
}
