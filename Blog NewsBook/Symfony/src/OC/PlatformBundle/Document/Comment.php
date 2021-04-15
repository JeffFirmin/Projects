<?php
// src/OC/PlatformBundle/Document/Comment.php

namespace  OC\PlatformBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;
use FOS\CommentBundle\Document\Comment as BaseComment;
use FOS\CommentBundle\Model\VotableCommentInterface;


/**
 * @MongoDB\Document
 * @MongoDB\ChangeTrackingPolicy("DEFERRED_EXPLICIT")
 */
class Comment extends BaseComment
{
    /**
     * @MongoDB\Id
     */
    protected $id;

    /**
     * Thread of this comment
     *
     * @var Thread
     * @MongoDB\ReferenceOne(targetDocument="OC\PlatformBundle\Document\Thread")
     */
    protected $thread;

    /**
     * @MongoDB\Field(type="int")
     * @var int
     */
    protected $score = 0;

    /**
     * Sets the score of the comment.
     *
     * @param integer $score
     */
    public function setScore($score)
    {
        $this->score = $score;
    }

    /**
     * Returns the current score of the comment.
     *
     * @return integer
     */
    public function getScore()
    {
        return $this->score;
    }

    /**
     * Increments the comment score by the provided
     * value.
     *
     * @param integer value
     *
     * @return integer The new comment score
     */
    public function incrementScore($by = 1)
    {
        $this->score += $by;
    }
}