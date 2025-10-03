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

     public boolean equals(Object obj){
     if(this == obj){
          return true;
     }
     if(this.getClass() != obj.getClass()){
          return false;
     }
     if (!(super.equals(obj))){
          return false;
     } 
     
     Ata other = (Ata) obj;
     return this.numero == other.numero && this.texto.equals(other.texto) && DocumentoUtils.arraysDeStringSaoIguais(this.presentes, other.presentes);
    }

}
