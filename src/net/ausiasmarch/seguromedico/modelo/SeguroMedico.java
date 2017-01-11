package net.ausiasmarch.seguromedico.modelo;

/**
 *
 * @author Luis
 */
public class SeguroMedico {

    // Propiedades
    private String genero, trabajo;
    private int edad, numHijos;
    private double precioBase;
    private boolean embarazada;
    private boolean enfermedadCorazon, enfermedadOjos, enfermedadPiel;
    private boolean coberturaCorazon, coberturaOftalmologica, coberturaInVitro;
    private boolean coberturaPediatria, coberturaDermatologia;
    private String mensaje;
    private StringBuilder sb;

    // Metodos get y set
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    public boolean isEnfermedadCorazon() {
        return enfermedadCorazon;
    }

    public void setEnfermedadCorazon(boolean enfermedadCorazon) {
        this.enfermedadCorazon = enfermedadCorazon;
    }

    public boolean isEnfermedadOjos() {
        return enfermedadOjos;
    }

    public void setEnfermedadOjos(boolean enfermedadOjos) {
        this.enfermedadOjos = enfermedadOjos;
    }

    public boolean isEnfermedadPiel() {
        return enfermedadPiel;
    }

    public void setEnfermedadPiel(boolean enfermedadPiel) {
        this.enfermedadPiel = enfermedadPiel;
    }

    public boolean isCoberturaCorazon() {
        return coberturaCorazon;
    }

    public void setCoberturaCorazon(boolean coberturaCorazon) {
        this.coberturaCorazon = coberturaCorazon;
    }

    public boolean isCoberturaOftalmologica() {
        return coberturaOftalmologica;
    }

    public void setCoberturaOftalmologica(boolean coberturaOftalmologica) {
        this.coberturaOftalmologica = coberturaOftalmologica;
    }

    public boolean isCoberturaInVitro() {
        return coberturaInVitro;
    }

    public void setCoberturaInVitro(boolean coberturaInVitro) {
        this.coberturaInVitro = coberturaInVitro;
    }

    public boolean isCoberturaPediatria() {
        return coberturaPediatria;
    }

    public void setCoberturaPediatria(boolean coberturaPediatria) {
        this.coberturaPediatria = coberturaPediatria;
    }

    public boolean isCoberturaDermatologia() {
        return coberturaDermatologia;
    }

    public void setCoberturaDermatologia(boolean coberturaDermatologia) {
        this.coberturaDermatologia = coberturaDermatologia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public boolean validate() {

        sb = new StringBuilder();
        //- La edad debe ser mayor o igual de 18 años 
        if (edad < 18) {
            sb.append("- La edad debe ser mayor o igual de 18 años\n ");
        }
        //- El número de hijos debe ser mayor o igual que cero 
        if (numHijos < 0) {
            sb.append("- El número de hijos debe ser mayor o igual que cero\n ");
        }
        //- Si eligió ’‘Embarazada’ o cobertura ‘Fecundación in vitro, el sexo debe ser ‘Mujer’. 
        if (embarazada || coberturaInVitro) {
            if (genero.equalsIgnoreCase("Hombre")) {
                sb.append("- Si eligió ’‘Embarazada’ o cobertura ‘Fecundación in vitro, el sexo debe ser ‘Mujer’.\n ");
            }
        }
        //Si eligió cobertura ‘Pediatría’ el número de hijos debe ser mayor de cero 
        if (coberturaPediatria) {
            if (numHijos <= 0) {
                sb.append("- Si eligió cobertura ‘Pediatría’ el número de hijos debe ser mayor de cero\n ");
            }
        }
        //- Si seleccionó la cobertura ‘Dermatológica” debe seleccionar la enfermedad ‘Piel’
        if (coberturaDermatologia) {
            if (enfermedadPiel == false) {
                sb.append("- Si seleccionó la cobertura ‘Dermatológica” debe seleccionar la enfermedad ‘Piel’\n");
            }
        }
        //Si seleccionó la cobertura ‘Corazón” debe seleccionar la enfermedad ‘Corazón’
        if (coberturaCorazon) {
            if (enfermedadCorazon == false) {
                sb.append("- Si seleccionó la cobertura ‘Corazón” debe seleccionar la enfermedad ‘Corazón’\n");
            }
        }
        //Si seleccionó la cobertura Oftalmología” debe seleccionar la enfermedad ‘Ojos’ 
        if (coberturaOftalmologica) {
            if (enfermedadOjos == false) {
                sb.append("- Si seleccionó la cobertura Oftalmología” debe seleccionar la enfermedad ‘Ojos’\n ");
            }
        }

        mensaje = sb.toString();
        return mensaje.isEmpty();
    }

    /**
     * Obtiene el importe mensual del seguro
     *
     * @return double
     */
    public double importeMensual() {
        int contadorEnfermedad = 0;

        if (enfermedadCorazon) {
            contadorEnfermedad++;
        }
        if (enfermedadOjos) {
            contadorEnfermedad++;
        }
        if (enfermedadPiel) {
            contadorEnfermedad++;
        }

        switch (contadorEnfermedad) {
            case 1:
                precioBase = precioBase + 15;
                break;
            case 2:
                precioBase = precioBase + 30;
                break;
            case 3:
                precioBase = precioBase + 45;
                break;
        }
        if (coberturaOftalmologica || coberturaPediatria) {
            precioBase = precioBase + 30;
        }
        if (coberturaInVitro) {
            precioBase = precioBase + 100;
        }
        if (coberturaCorazon) {
            precioBase = precioBase + 80;
        }
        if (coberturaDermatologia) {
            precioBase = precioBase + 40;
        }

        return precioBase;
    }

    /**
     * Obtiene el descuento del seguro
     *
     * @return int
     */
    private double descuento() {
        int descuentoAplicado = 0;
        double desc;
        if (trabajo.equalsIgnoreCase(TipoTrabajo.FUNCIONARIO.toString())) {
            descuentoAplicado = 15;
        }else{
            descuentoAplicado = 5;
        }

        desc = (importeMensual() * 12) * descuentoAplicado / 100;

        return desc;
    }

    /**
     * Obtiene el importe anual
     *
     * @return double
     */
    public double importeAnual() {

        double imp;
        imp = (importeMensual() * 12) - descuento();

        return imp;
    }

}
