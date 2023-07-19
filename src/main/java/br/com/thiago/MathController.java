package br.com.thiago;

import br.com.thiago.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong contador = new AtomicLong();

    @RequestMapping(value = "/soma/{n1}/{n2}", method = RequestMethod.GET) // ou @GetMapping(value = "/soma/{n1}/{n2}")
    public Double soma(@PathVariable(value = "n1") String n1, @PathVariable(value = "n2") String n2) throws Exception {

        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        // BR 10,25 US 10.25
        String numero1 = n1.replaceAll(",", ".");
        String numero2 = n2.replaceAll(",", ".");

        Double resultado = Double.parseDouble(numero1) + Double.parseDouble(numero2);

        return resultado;
    }

    @GetMapping("/subtração/{n1}/{n2}")
    public Double subtracao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");
        String numero2 = n2.replaceAll(",", ".");

        Double resultado;
        return resultado = Double.parseDouble(numero1) - Double.parseDouble(numero2);
    }

    @GetMapping("/multiplicacao/{n1}/{n2}")
    public Double multiplicacao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");
        String numero2 = n2.replaceAll(",", ".");

        Double resultado;
        return resultado = Double.parseDouble(numero1) * Double.parseDouble(numero2);
    }

    @GetMapping("/dividir/{n1}/{n2}")
    public Double divisao(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");
        String numero2 = n2.replaceAll(",", ".");

        Double resultado;
        return resultado = Double.parseDouble(numero1) / Double.parseDouble(numero2);
    }

    @GetMapping("/media/{n1}/{n2}")
    public Double media(@PathVariable("n1") String n1, @PathVariable(value = "n2") String n2) {
        if (!isNumeric(n1) || !isNumeric(n2)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");
        String numero2 = n2.replaceAll(",", ".");

        Double resultado = Double.parseDouble(numero1) + Double.parseDouble(numero2);

        return resultado / 2;
    }

    @GetMapping("/squareroot/{n1}")
    public Double raizQuadrada(@PathVariable("n1") String n1) {
        if (!isNumeric(n1)) {
            throw new UnsupportedMathOperationException("Por favor setar um valor numérico!");
        }

        String numero1 = n1.replaceAll(",", ".");

        Double numeroConvertido = Double.parseDouble(numero1);

        return Math.sqrt(numeroConvertido);
    }

    private boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }

        String numero = str.replaceAll(",", ".");

        return numero.matches("[-+]?[0-9]*\\.?[0-9]+");

    }
}
