package SocieteEgaGroup.SocieteEga;

import SocieteEgaGroup.SocieteEga.model.Compte;
import SocieteEgaGroup.SocieteEga.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SocieteEgaApplication {

	private static CompteService compteService;

	@Autowired
	public void setCompteService(CompteService compteService) {
		SocieteEgaApplication.compteService = compteService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SocieteEgaApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		int option = 0;
		while (true) {
			System.out.println("Bonjour et bienvenue sur notre application de Gestion Bancaire ");
			System.out.println("");
			System.out.println("Que puis je faire pour vous ?");
			System.out.println("");
			System.out.println("1-Faire un versement sur son compte ");
			System.out.println("");
			System.out.println("2- Faire un retrait de son compte");
			System.out.println("");
			System.out.println("3- Faire un virement");
			System.out.println("");
			System.out.print("Entrer votre choix : ");
			option = scanner.nextInt();
			if (option > 0 && option < 4) {
				System.out.println("Vous avez entré le nombre : " + option);
				break;
			} else {
				System.out.println("Option invalide, veuillez entrer une option valide.");
			}
		}
		switch (option) {
			case 1:
				// Demander le numéro de compte à afficher
				Scanner scanne = new Scanner(System.in);
				System.out.print("Entrez le numéro de compte sur lequel faire le depot: ");
				String numeroCompte = scanne.nextLine();

				// Afficher le compte correspondant
				Compte compte = compteService.getCompteByNumero(numeroCompte);
				if (compte == null) {
					System.out.println("Le compte avec le numéro " + numeroCompte + " n'a pas été trouvé.");
				} else {
					System.out.println("Entrez le montant à verser : ");
					double montant = scanner.nextDouble();
					// Mettre à jour le solde du compte

					Compte compteMisAJour = compteService.Versement(numeroCompte, (int) montant);

					// Afficher les nouvelles informations du compte
					System.out.println("Transactions Effectuée avec succes : ");
					System.out.print("Votre nouveaux solde est de: "+compteMisAJour.getSolde());
				}
				break;

			case 2:
				// Demander le numéro de compte à afficher
				Scanner scanner2 = new Scanner(System.in);
				System.out.print("Entrez le numéro de compte sur lequel retirer l' argent  : ");
				String numeroCompt = scanner2.nextLine();

				// Afficher le compte correspondant
				Compte compt = compteService.getCompteByNumero(numeroCompt);
				if (compt == null) {
					System.out.println("Le compte avec le numéro " + numeroCompt + " n'a pas été trouvé.");
				} else {
					System.out.println("Entrez le montant à retirer : ");
					double montant = scanner.nextDouble();
					// Mettre à jour le solde du compte

					Compte compteMisAJour = compteService.Retrait(numeroCompt, (int) montant);

					// Afficher les nouvelles informations du compte
					System.out.println("Transactions Effectuée avec succes : ");
					System.out.print("Votre nouveaux solde est de: "+compteMisAJour.getSolde());
				}
				break;

			case 3:
				Scanner scanner3 = new Scanner(System.in);
				System.out.print("Entrez le numéro de compte sur lequel retirer l' argent  : ");
				String numCompteSrc = scanner3.nextLine();

				Compte compteSrc = compteService.getCompteByNumero(numCompteSrc);
				if (compteSrc == null) {
					System.out.println("Le compte avec le numéro " + numCompteSrc + " n'a pas été trouvé.");
				} else {
					Scanner scanner4 = new Scanner(System.in);
					System.out.print("Entrez le numéro de compte sur lequel faire le depot: ");
					String numCompteDest = scanner4.nextLine();

					Compte compteDest = compteService.getCompteByNumero(numCompteDest);
					if (compteDest == null) {
						System.out.println("Le compte avec le numéro " + numCompteDest + " n'a pas été trouvé.");
					} else {
						System.out.println("Entrez le montant à retirer : ");
						double montant = scanner.nextDouble();

						Compte compteVirement = compteService.Virement(numCompteSrc, numCompteDest,  montant);

						// Afficher les nouvelles informations du compte
						System.out.println("Transactions Effectuée avec succes : ");
						System.out.print("Votre nouveaux solde sur le compte "+numCompteSrc+" est de: "+(compteSrc.getSolde()-montant));
						System.out.print("Votre nouveaux solde sur le compte "+numCompteDest+" est de: "+(compteDest.getSolde()+montant));
					}

				}
				break;
			default:
				System.out.println("Option invalide, veuillez entrer une option valide.");
				break;
		}



	}



}





