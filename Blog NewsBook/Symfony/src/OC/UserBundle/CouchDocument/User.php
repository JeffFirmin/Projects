<?php
// src/OC/UserBundle/CouchDocument/User.php

namespace OC\UserBundle\CouchDocument;

use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ODM\CouchDB\Mapping\Annotations as CouchDB;

/**
 * @CouchDB\Document
 */
class User extends BaseUser
{
    /**
     * @CouchDB\Id
     */
    protected $id;

    /*public function __construct()
    {
        parent::__construct();
        // your own logic
    }
    */
}