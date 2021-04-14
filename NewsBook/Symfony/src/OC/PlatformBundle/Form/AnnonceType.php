<?php
// src/OC/PlatformBundle/Form/AnnonceType.php

namespace OC\PlatformBundle\Form;

use OC\PlatformBundle\Repository\RubriqueRepository;
use OC\PlatformBundle\Repository\SousrubriqueRepository;
use OC\PlatformBundle\Repository\UserRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Security\Core\SecurityContext;
use OC\UserBundle\Entity\User;



//classe pour définir le formulaire d'ajout d'annonce
class AnnonceType extends AbstractType
{
  
  public function buildForm(FormBuilderInterface $builder, array $options)
  {


    // Arbitrairement, on récupère toutes les catégories qui commencent par "D"
    $pattern = 'D%';
    
    $builder
      //->add('date',      DateTimeType::class)
      ->add('title',     TextType::class)
      ->add('url',       TextType::class)
      ->add('content',   TextareaType::class)
      //on ajoute le champ image facultatif, si l'utilisateur veut il rajoute sinon il laisse le champ à null
      ->add('image',     ImageType::class, array('required' => false))

      /*
       ** - 1er argument : nom du champ, ici « categories », car c'est le nom de l'attribut
       ** - 2e argument : type du champ, ici « CollectionType » qui est une liste de quelque chose
       ** - 3e argument : tableau d'options du champ
       */
      ->add('rubrique', EntityType::class, array(
        'class'         => 'OCPlatformBundle:Rubrique',
        'choice_label'  => 'name',
        'multiple'      => false,
        /*'query_builder' => function(SouscategoryRepository $repository) use($pattern) {
          return $repository->getLikeQueryBuilder($pattern);

        }   */
      ))

      ->add('sousrubriques', EntityType::class, array(
        'class'         => 'OCPlatformBundle:Sousrubrique',
        'choice_label'  => 'name',
        'multiple'      => true,
       
      ))
    
      ->add('sauvegarder',      SubmitType::class)
    ;

    // On ajoute une fonction qui va écouter un évènement
    $builder->addEventListener(
      FormEvents::PRE_SET_DATA,    // 1er argument : L'évènement qui nous intéresse : ici, PRE_SET_DATA
      function(FormEvent $event) { // 2e argument : La fonction à exécuter lorsque l'évènement est déclenché
        // On récupère notre objet Annonce sous-jacent

        $annonce = $event->getData();
 
        if (null === $annonce) {
          return; // On sort de la fonction sans rien faire lorsque $annonce vaut null
        }

        // Si l'annonce n'est pas publiée, ou si elle n'existe pas encore en base (id est null)
        if (!$annonce->getPublished() || null === $annonce->getId()) {
          // Alors on ajoute le champ published
          $event->getForm()->add('published', CheckboxType::class, array('required' => false));
        } else {
          // Sinon, on le supprime
          $event->getForm()->remove('published');
        }

      });
} 

  public function configureOptions(OptionsResolver $resolver)
  {
    $resolver->setDefaults(array(
      'data_class' => 'OC\PlatformBundle\Entity\Annonce'
    ));
  }
}
