import java.util.Scanner; import java.util.Random;

public class Main {  
    // Variáveis globais
    static final int energiaFogo = 5, energiaNegra = 10, energiaCura = 10;
    static int contador = 0;
    static char comandoChar;
    // variáveis do agente
    static int danoRealAgente = 20, PvA = 100, MnA = 20, FrA = 10, PeA = 0, CrA = 3;
    // variáveis do monstro
    static int danoRealMonstro = 10, pvMonstro = 200, manaMonstro, CrAMonstro = 3;

    //Game Over
    public static void gameOver() {
        Scanner cmdGameOver = new Scanner(System.in);
        if (pvMonstro < 1) {System.out.println("Você venceu a batalha!");}
        if (PvA < 1) {
            do {
            System.out.println("\n\nVocê perdeu!\nDeseja continuar? \n[1] SIM\n[2] NÃO");
            comandoChar = cmdGameOver.nextLine().charAt(0);
            switch (comandoChar) {
            case '1':
                PvA = 100;
                pvMonstro = 200;
                executarCombate();
            break;
            case '2': 
                System.exit(0);
            break;
            default: 
                System.out.println("Digite um comando valido!");
            } 
        } while (PvA < 1);
        }
    }

    //Turnos
    public static void avancarTurno(){
      if(PvA > 0 && pvMonstro > 0) {
        contador += 1;
        danoFisicoMonstro(danoRealMonstro);
        try {Thread.sleep(1000);} catch (Exception erro){}
        System.out.println("Vida do Agente: " + PvA);
        try {Thread.sleep(2000);} catch (Exception erro){}
      }
    }

    //Verificações das magias

      public static void verificarFogo() {
       if (PeA <= energiaFogo) {
         noMana();
       } 
      }

      public static void verificarMagiaNegra() {
        if (PeA <= energiaNegra) {
          noMana();
        }  
      }

      public static void verificarCura() {
        if (PeA <= energiaCura) {
          noMana();
        }
      }
  
      public static void getBolaDeFogo() {
      if (PeA >= 5) {
      bolaDeFogo(MnA);
      } else {
        noMana();
      }
    }

    public static void getMagiaNegra() {
      if (PeA >= 10) {
        magiaNegra(MnA);
      } else {
        noMana();
      }
    }

    public static void getCurar() {
      if (PeA >= 5) {
        curarAgente(MnA);
      } else {
        noMana();
        }
      }
  
    public static void noMana() {
        System.out.println("\nVocê não tem energia suficiente\n");
        try {Thread.sleep(1000);} catch (Exception erro){}
    }

    //Magias
    public static int bolaDeFogo(int danoBolaDeFogo){
        PeA -= 5;
        Random dano = new Random();
        danoBolaDeFogo = dano.nextInt(MnA + 1 - (MnA / 2)) + MnA;
        danoBolaDeFogo += MnA * 0.50;
        pvMonstro -= danoBolaDeFogo;
        if (pvMonstro < 0) 
        pvMonstro = 0;
        System.out.println("\nVocê incendeia o monstro com uma poderosa bola de fogo e causa " + danoBolaDeFogo + " de dano");
        try {Thread.sleep(3000);} catch (Exception erro){}
        System.out.println("Vida do monstro: " + pvMonstro);
        try {Thread.sleep(3000);} catch (Exception erro){}
        return danoBolaDeFogo;
    }

    public static int magiaNegra(int danoMagiaNegra){
        int retornarDano;
        PeA -= 10;
        Random dano = new Random();
        danoMagiaNegra = dano.nextInt((MnA + 1) - 5) + MnA;
        danoMagiaNegra += MnA * 0.50;
        pvMonstro -= danoMagiaNegra;
        if (pvMonstro < 0) 
          pvMonstro = 0;
        System.out.println("\nVocê manipulou as forças do vazio e desferiu um ataque causando " + danoMagiaNegra + " de dano");
        try {Thread.sleep(3000);} catch (Exception erro){}
        System.out.println("Vida do monstro: " + pvMonstro);   
        retornarDano = dano.nextInt(danoMagiaNegra);
        PvA -= retornarDano;
        try {Thread.sleep(3000);} catch (Exception erro){}
        System.out.println("\nVocê sente sua força vital se esvaindo");
        try {Thread.sleep(3000);} catch (Exception erro){}
        System.out.println("Você perde " + retornarDano + " pontos de vida" );
        try {Thread.sleep(3000);} catch (Exception erro){}   
        return danoMagiaNegra;
    }

    public static int curarAgente(int quantidadeCura){
        Random cura = new Random(); 
        PeA -= 10;
        quantidadeCura = cura.nextInt((MnA + 1) - 5) + 30;
        System.out.println("Você canaliza a energia ao redor e restaura " + quantidadeCura + " pontos de vida");
        PvA += quantidadeCura;
        if (PvA > 100)
        PvA = 100;
        try {Thread.sleep(3000);} catch (Exception erro){}
        return quantidadeCura;
    }

    // Menu das magias
    public static void escolherMagias() {
        switch (comandoChar) {
        case '1':
            
            if (CrA >= CrAMonstro) {
              getBolaDeFogo();
              avancarTurno();
            } else {
              if (PeA >= 5) {
              avancarTurno();
              getBolaDeFogo();
              } else {noMana();}
            }
            break;
        case '2':
            if (CrA >= CrAMonstro) {
              getMagiaNegra();
              avancarTurno();
            } else {
              if (PeA >= 5) {
              avancarTurno();
              getMagiaNegra();
              } else {noMana();}
            }
            break;
        case '3':
            if (CrA >= CrAMonstro) {
              getCurar();
              avancarTurno();
            } else {
              if (PeA >= 5) {
              avancarTurno();
              getCurar();
              } else {noMana();}
            }
        case '4':
            executarCombate();
        default:
            System.out.println("\nDigite uma opção valida!");
            try {Thread.sleep(3000);} catch (Exception erro){}
            //Magias();
        } 
}

    // Dano do Agente
    public static int danoFisico(int danoAgente){
        Random dano = new Random();
        danoAgente = dano.nextInt(danoRealAgente + 1 - FrA) + FrA;
        danoAgente += FrA * 0.25;
        pvMonstro -= danoAgente;
        if (pvMonstro < 0) 
          pvMonstro = 0;
        System.out.println("\nVocê ataca o monstro e causa " + danoAgente + " de dano");
        return danoAgente;
    }

    // Dano do Monstro
    public static int danoFisicoMonstro(int danoMonstro){
        Random dano = new Random();
        danoMonstro = dano.nextInt(danoRealMonstro + 1 - 5) + 5;
        PvA -= danoMonstro;
        if (PvA < 0)
          PvA = 0;
        System.out.println("\nO monstro te atacou e causou " + danoMonstro + " de dano");
        return danoMonstro;
    }

    // Executar o combate


    public static void executarCombate() {
        Scanner cmdExecutarCombate = new Scanner(System.in);
        //char comandoChar;
        do { String div = "----------------------------------";
        System.out.println(div + "\n");
        System.out.println("O que deseja fazer?\n");
        System.out.println("[1]Atacar                [2]Magia\n\n[3]Inventário            [4]Status");
        System.out.println("\n" + div);
        System.out.print("\nAção: ");
        comandoChar = cmdExecutarCombate.next().charAt(0);

        switch (comandoChar) {
            case '1':
            if (CrA >= CrAMonstro) {
                danoFisico(danoRealAgente);
                try {Thread.sleep(1000);} catch (Exception erro){}
                System.out.println("Vida do monstro: " + pvMonstro);
                try {Thread.sleep(2000);} catch (Exception erro){}
                avancarTurno();
            }
            else { 
                  avancarTurno();
                  danoFisico(danoRealAgente);
                  try {Thread.sleep(1000);} catch (Exception erro){}
                  System.out.println("Vida do monstro: " + pvMonstro);
                  try {Thread.sleep(2000);} catch (Exception erro){}
                 }
            break;
            
            case '2':
                System.out.println("\n[1]Bola de Fogo         [2]Magia Negra\n\n[3]Curar                [4]Voltar");
                System.out.print("\nAção: ");
                comandoChar = cmdExecutarCombate.next().charAt(0);
                escolherMagias();
                break;

            case '3':
                System.out.println("\nVocê abriu o inventario");
                try {Thread.sleep(1000);} catch (Exception erro){}
                break;

            case '4':
                System.out.println("\n[STATUS]\n\nVida do Agente: " + PvA + "\n\nEnergia do Agente: " + PeA + "\n\nVida do Monstro: " + pvMonstro + "\n");
                try {Thread.sleep(5000);} catch (Exception erro){}
                break;

            default:
                System.out.println("\nDigite uma opção valida!");
                try {Thread.sleep(1000);} catch (Exception erro){}
            } } while (pvMonstro > 0 && PvA > 0);
    }

public static void main(String[] args) {
        executarCombate();
        gameOver();
    } 
}