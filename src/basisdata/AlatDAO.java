/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basisdata;

import java.util.List;
import eksepsi.KesalahanBasisDataException;
import model.Alat;

/**
 *
 * @author acka
 */
public interface AlatDAO {
    
    void simpan(Alat alat) throws KesalahanBasisDataException;
    void perbarui(Alat alat) throws KesalahanBasisDataException;
    void hapus(String kodeAlat) throws KesalahanBasisDataException;
    Alat cariKode(String kodeAlat) throws KesalahanBasisDataException;
    List<Alat> semua() throws KesalahanBasisDataException;
    String namaSumber();
}

