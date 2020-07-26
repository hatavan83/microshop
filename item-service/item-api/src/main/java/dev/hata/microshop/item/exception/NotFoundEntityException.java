package dev.hata.microshop.item.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class NotFoundEntityException extends RuntimeException {
    final private int statusCode = 404;
    final static String errorMsg = "Cannot found {0} by id={1}";

    public NotFoundEntityException(Class clazz, String entityId) {
        super(MessageFormat.format(errorMsg, clazz.getSimpleName(), entityId));
    }
}
