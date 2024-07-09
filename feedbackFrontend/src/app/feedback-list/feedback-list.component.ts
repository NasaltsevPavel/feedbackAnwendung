import {Component} from '@angular/core';
import {FeedbackService} from "../service/feedback.service";
import {Feedback} from "../model/Feedback";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-feedback-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './feedback-list.component.html',
  styleUrl: './feedback-list.component.css'
})
export class FeedbackListComponent {
  feedbacks: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.getAllFeedback();
  }

  getAllFeedback(): void {
    this.feedbackService.getAllFeedback().subscribe({
      next:  (data: Feedback[]) => {
        this.feedbacks = data;
      }
    })
  }
}
