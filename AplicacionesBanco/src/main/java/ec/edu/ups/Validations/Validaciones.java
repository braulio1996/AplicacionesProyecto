package ec.edu.ups.Validations;

public class Validaciones {
    
    public boolean validaCedula(String cedula) throws Exception{
        boolean cedulaCorrecta = false;
        
        try { 
            if (cedula.length() == 10){
                int prov = Integer.parseInt(cedula.substring(0, 2));
                
                if(prov>=1 && prov<=24){
                    int tercerDigito = Integer.parseInt(cedula.substring(2, 3));

                    if (tercerDigito < 6) {
                        int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                        int verificador = Integer.parseInt(cedula.substring(9,10));
                        int suma = 0;
                        int digito = 0;

                        for (int i = 0; i < (cedula.length() - 1); i++) {
                            digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                            suma += ((digito % 10) + (digito / 10));
                        }//End for

                        if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                            cedulaCorrecta = true;
                        }else if ((10 - (suma % 10)) == verificador) {
                            cedulaCorrecta = true;
                        } else {
                            cedulaCorrecta = false;
                        }//End if ((suma % 10 == 0) && (suma % 10 == verificador))
                    } else {
                        cedulaCorrecta = false;
                    }//End if (tercerDigito < 6)
                }else{
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }//End if (cedula.length() == 10)
        } catch (NumberFormatException nfe) {
            throw new Exception(nfe.getCause());
        } catch (Exception err) {
            throw new Exception(err.getCause());
        }//End try/catch

        return cedulaCorrecta;
    }//End method cedula
}
