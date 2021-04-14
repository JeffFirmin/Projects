<?php
// src/OC/PlatformBundle/Form/CategoryType.php

namespace OC\PlatformBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use OC\UserBundle\Entity\User;
use OC\PlatformBundle\Entity\Advert;
use Symfony\Component\Security\Core\SecurityContext;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\DependencyInjection\ContainerInterface as Container;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;


//classe pour le champ image du formulaire
class UserType extends AbstractType
{ 
   
  public function buildForm(FormBuilderInterface $builder, array $options)
  {
   
    $builder
       ->add('username');
      
       ->add('sauvegarder',      SubmitType::class) ;
    
  }

   
   public function getUser()
    {       
        return 'user';
    }
   


  public function configureOptions(OptionsResolver $resolver)
  {
    $resolver->setDefaults(array(
      'data_class' => 'OC\UserBundle\Entity\User'
    ));
  }

}
