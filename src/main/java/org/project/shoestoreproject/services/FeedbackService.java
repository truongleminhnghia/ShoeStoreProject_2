package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Feedback;

public interface FeedbackService {
    public Feedback createFeedback(Feedback feedback);
    public Feedback getFeedback(int feedbackId);
}
