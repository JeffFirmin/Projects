<?php
// src/OC/PlatformBundle/Form/AnnoncetEditType.php

namespace OC\PlatformBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;


//classe pour la modification d'une annoce qui hérite de AnnonceType
class AnnonceEditType extends AbstractType
{
  public function buildForm(FormBuilderInterface $builder, array $options)
  {
    $builder->remove('date');
  }

  public function getParent()
  {
    return AnnonceType::class;
  }
}
