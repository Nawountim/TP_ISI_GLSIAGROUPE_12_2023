package TPISIGLSI.CrudApi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

enum Sexe {
 HOMME,
 FEMME,
 AUTRE
}

@Entity
public class Client {

 private String nom;
 private String prenom;
 private String dateNaissance;
 private Sexe sexe;
 private String adresse;
 private String numeroTelephone;
 private String courriel;
 private String nationalite;


 @OneToMany(mappedBy = "client")
 private List<Compte> comptes;

 public Client(String nom, String prenom, String dateNaissance, Sexe sexe, String adresse,
               String numeroTelephone, String courriel, String nationalite) {
  this.nom = nom;
  this.prenom = prenom;
  this.dateNaissance = dateNaissance;
  this.sexe = sexe;
  this.adresse = adresse;
  this.numeroTelephone = numeroTelephone;
  this.courriel = courriel;
  this.nationalite = nationalite;
 }

 // Getters and Setters

 public String getNom() {
  return nom;
 }

 public void setNom(String nom) {
  this.nom = nom;
 }

 public String getPrenom() {
  return prenom;
 }

 public void setPrenom(String prenom) {
  this.prenom = prenom;
 }

 public String getDateNaissance() {
  return dateNaissance;
 }

 public void setDateNaissance(String dateNaissance) {
  this.dateNaissance = dateNaissance;
 }

 public Sexe getSexe() {
  return sexe;
 }

 public void setSexe(Sexe sexe) {
  this.sexe = sexe;
 }

 public String getAdresse() {
  return adresse;
 }

 public void setAdresse(String adresse) {
  this.adresse = adresse;
 }

 public String getNumeroTelephone() {
  return numeroTelephone;
 }

 public void setNumeroTelephone(String numeroTelephone) {
  this.numeroTelephone = numeroTelephone;
 }

 public String getCourriel() {
  return courriel;
 }

 public void setCourriel(String courriel) {
  this.courriel = courriel;
 }

 public String getNationalite() {
  return nationalite;
 }

 public void setNationalite(String nationalite) {
  this.nationalite = nationalite;
 }


}
