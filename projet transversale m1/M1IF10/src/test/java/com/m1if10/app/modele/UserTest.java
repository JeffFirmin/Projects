package com.m1if10.app.modele;

import com.m1if10.app.classes.CryptageMdp;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getEmail() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        String tmp = u.getEmail();
        //Then
        assertEquals("user@user.fr", tmp);
    }

    @Test
    public void setEmail() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
         u.setEmail("bob@dylan.fr");
        //Then
        assertEquals("bob@dylan.fr",u.getEmail());
    }

    @Test
    public void getPassword() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        String tmp = u.getPassword();
        //Then
        assertEquals(CryptageMdp.encrypt("toto"),tmp);
    }

    @Test
    public void setPassword() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        u.setPassword(CryptageMdp.encrypt("titi"));
        //Then
        assertEquals(u.getPassword(),CryptageMdp.encrypt("titi"));
    }

    @Test
    public void getNom() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        String tmp = u.getNom();
        //Then
        assertEquals("dylan",tmp);
    }

    @Test
    public void setNom() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        u.setNom("marco");
        //Then
        assertEquals("marco",u.getNom());
    }

    @Test
    public void getPrenom() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        String tmp = u.getPrenom();
        //Then
        assertEquals("bob",tmp);
    }

    @Test
    public void setPrenom() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "");
        //When
        u.setPrenom("philippe");
        //Then
        assertEquals("philippe",u.getPrenom());
    }

    @Test
    public void getUrlPhoto() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "/img/user.jpg");
        //When
        String tmp = u.getUrlPhoto();
        //Then
        assertEquals("/img/user.jpg",tmp);
    }

    @Test
    public void setUrlPhoto() {
        // Given
        User u = new User("user@user.fr", CryptageMdp.encrypt("toto"), "dylan", "bob", "/img/user.jpg");
        //When
        u.setUrlPhoto("/img/1/user.jpg");
        //Then
        assertEquals("/img/1/user.jpg",u.getUrlPhoto());
    }

}