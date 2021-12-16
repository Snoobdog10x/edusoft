package com.company.Class;

public class VienChucNhomLop {
    private int Manhomlop,MVC;

    public VienChucNhomLop(int manhomlop, int MVC) {
        Manhomlop = manhomlop;
        this.MVC = MVC;
    }

    public int getManhomlop() {
        return Manhomlop;
    }

    public void setManhomlop(int manhomlop) {
        Manhomlop = manhomlop;
    }

    public int getMVC() {
        return MVC;
    }

    public void setMVC(int MVC) {
        this.MVC = MVC;
    }

    public Object[] toObjectArray(){
        Object[] objects={Manhomlop,MVC};
        return objects;
    }
    @Override
    public String toString() {
        return "VienChucNhomLop{" +
                "Manhomlop=" + Manhomlop +
                ", MPH=" + MVC +
                '}';
    }
}
