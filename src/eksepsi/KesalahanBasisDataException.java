package eksepsi;

public class KesalahanBasisDataException extends SipalabException {
    private static final long serialVersionUID = 1L;
    
   public KesalahanBasisDataException(String pesan){
       super(pesan);
       
   }

    public KesalahanBasisDataException(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }
}