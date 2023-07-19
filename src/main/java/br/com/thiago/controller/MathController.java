package br.com.thiago.controller;

import br.com.thiago.converter.ConverterToNumber;
import br.com.thiago.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static br.com.thiago.converter.ConverterToNumber.isNumeric;

@RestController
public class MathController {

    private final AtomicLong contador = new AtomicLong();

    @RequestMapping(value = "/soma/{n1}/{n2}", method = RequestMethod.GET) // ou @GetMapping(value = "/soma/{n1}/{n2}")
    public Double soma(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) throws Exception {

        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        Double resultado = ConverterToNumber.convertToDouble(n1) + ConverterToNumber.convertToDouble(n2);

        return resultado;
    }

    @GetMapping("/subtração/{n1}/{n2}")
    public Double subtracao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        Double resultado;
        return resultado = ConverterToNumber.convertToDouble(n1) - ConverterToNumber.convertToDouble(n2);
    }

    @GetMapping("/multiplicacao/{n1}/{n2}")
    public Double multiplicacao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        Double resultado;
        return resultado = ConverterToNumber.convertToDouble(n1) * Double.parseDouble(n2);
    }

    @GetMapping("/dividir/{n1}/{n2}")
    public Double divisao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        Double resultado;
        return resultado = ConverterToNumber.convertToDouble(n1) / ConverterToNumber.convertToDouble(n2);
    }

    @GetMapping("/media/{n1}/{n2}")
    public Double media(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        Double resultado = ConverterToNumber.convertToDouble(n1) + ConverterToNumber.convertToDouble(n2);

        return resultado / 2;
    }

    @GetMapping("/squareroot/{n1}")
    public Double raizQuadrada(@PathVariable("n1") String n1) {
        if (!isNumeric(n1)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");

        Double numeroConvertido = ConverterToNumber.convertToDouble(n1);

        return Math.sqrt(numeroConvertido);
    }


}
