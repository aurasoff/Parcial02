package org.uniquindio.edu.co.poo.parcial02.model;

public class EmpresaInmobilaria {
    private String nombre;
    private String direccion;
    private static EmpresaInmobilaria empresaInmobiliaria;


    private EmpresaInmobilaria() {
        this.nombre = "HABITEK INMOBILIARIA";
        this.direccion = "centro comercial Bambusa plaza, Cl. 5 Nte. #18A-96 local 28, Armenia, Quindío";
    }

    public static EmpresaInmobilaria getInstance() {
        if (empresaInmobiliaria == null) {
            empresaInmobiliaria = new EmpresaInmobilaria();
        }

        return empresaInmobiliaria;
    }

}