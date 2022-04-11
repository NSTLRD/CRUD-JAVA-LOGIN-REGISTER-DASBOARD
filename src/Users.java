public class Users {
    public String USUARIO;
    public String NOMBRE;
    public String APELLIDO;
    public String TELEFONO;
    public String EMAIl;
    public String PASSWORD;
    public String CONFIRMPASSWORD;

        public Users(){}

    public Users(String USUARIO, String NOMBRE, String APELLIDO, String TELEFONO, String EMAIl, String PASSWORD, String CONFIRMPASSWORD) {
        this.USUARIO = USUARIO;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.TELEFONO = TELEFONO;
        this.EMAIl = EMAIl;
        this.PASSWORD = PASSWORD;
        this.CONFIRMPASSWORD = CONFIRMPASSWORD;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getEMAIl() {
        return EMAIl;
    }

    public void setEMAIl(String EMAIl) {
        this.EMAIl = EMAIl;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getCONFIRMPASSWORD() {
        return CONFIRMPASSWORD;
    }

    public void setCONFIRMPASSWORD(String CONFIRMPASSWORD) {
        this.CONFIRMPASSWORD = CONFIRMPASSWORD;
    }

    @Override
    public String toString() {
        return "Users{" +
                "USUARIO='" + USUARIO + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", APELLIDO='" + APELLIDO + '\'' +
                ", TELEFONO='" + TELEFONO + '\'' +
                ", EMAIl='" + EMAIl + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", CONFIRMPASSWORD='" + CONFIRMPASSWORD + '\'' +
                '}';
    }
}
