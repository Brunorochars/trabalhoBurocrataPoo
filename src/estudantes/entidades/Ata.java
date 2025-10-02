package estudantes.entidades;

public class Ata extends Documento {
   
   private int numero;
   private String texto;
   private String [] presentes;

   public Ata(int numero, String texto, String[] presentes){
    this.numero = numero;
    this.texto = texto;
    this.presentes = presentes;
   }

   public int getNumero(){
    return this.numero;
   }

   public String getTexto(){
    return this.texto;
   }

   public String[] getPresentes(){
    return this.getPresentes();
   }

   public int setNumero(int numero){
    return this.numero = numero;
   }

   public String setTexto(String texto){
    return this.texto = texto;
   }

   public String[] setPresentes(String[] presentes){
    return this.presentes = presentes;
   }

     public boolean comparaAta(Ata ata){
     if(this == ata){
          return true;
     }
     if(ata == null){
          return false;
     }
     if(this.getClass() != ata.getClass()){
          return false;
     }
     if(this.texto.equals(((Ata) ata).texto) && this.presentes.equals(((Ata) ata).presentes)){
          return true;
     } else {
          return false;
     }
    }

}
