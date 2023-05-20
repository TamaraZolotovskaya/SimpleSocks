package me.tamarazolotovskaya.simplesocks3.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectParamExeption extends RuntimeException {
}
