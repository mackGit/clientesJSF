package br.com.parceriasistemas.jsf.cd.servicos;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cpfValidator")
public class CPFValidator implements Validator {

    @Override
    public void validate(FacesContext content, UIComponent component, Object value) throws ValidatorException {
        if (!isCPFValido(value.toString())) {
            FacesMessage msg = new FacesMessage("Erro!", "CPF do cliente é inválido!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    private boolean isCPFValido(String cpf) {
        boolean result = false;

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        if (cpf.length() == 11) {
            int arrayCPF[] = new int[11];
            int dv1 = 0;
            int dv2 = 0;

            for (int i = 0; i < 11; i++) {
                arrayCPF[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            for (int i = 0; i < 9; i++) {
                dv1 += arrayCPF[i] * (i + 1);
            }

            arrayCPF[9] = dv1 = dv1 % 11;

            for (int i = 0; i < 10; i++) {
                dv2 += arrayCPF[i] * i;
            }

            arrayCPF[10] = dv2 = dv2 % 11;

            if (dv1 > 9) {
                arrayCPF[9] = 0;
            }

            if (dv2 > 9) {
                arrayCPF[10] = 0;
            }

            result = (Integer.parseInt(cpf.substring(9, 10)) == arrayCPF[9] && Integer.parseInt(cpf.substring(10, 11)) == arrayCPF[10]);
        }

        if (!result) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "CPF inválido!"));
        }

        return result;
    }
}
