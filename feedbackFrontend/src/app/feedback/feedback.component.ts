import {Component, OnInit} from '@angular/core';
import {MatError, MatFormField, MatHint, MatLabel} from "@angular/material/form-field";
import {MatSlideToggle} from "@angular/material/slide-toggle";
import {MatOption, MatSelect} from "@angular/material/select";
import {Role} from "../model/Role.enum";
import {NgForOf, NgIf} from "@angular/common";
import {MatCard, MatCardContent} from "@angular/material/card";
import {AI} from "../model/AI.enum";
import {MatIcon} from "@angular/material/icon";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {FeedbackCheck} from "../model/FeedbackCheck";
import {Feedback} from "../model/Feedback";
import {FeedbackService} from "../service/feedback.service";

@Component({
  selector: 'app-feedback',
  standalone: true,
  imports: [
    MatLabel,
    MatFormField,
    MatSlideToggle,
    MatSelect,
    MatOption,
    NgForOf,
    MatCardContent,
    MatCard,
    MatHint,
    MatIcon,
    MatButton,
    MatInput,
    FormsModule,
    NgIf,
    MatError
  ],
  templateUrl: './feedback.component.html',
  styleUrl: './feedback.component.css'
})
export class FeedbackComponent implements OnInit {

  roleOptions = Role.getRoleOptions();
  aiOptions = AI.getAIOptions();
  aiOption: string = '';
  check: boolean = false;

  feedbackCheck: FeedbackCheck = new FeedbackCheck();

  feedback: Feedback = new Feedback();

  constructor(
    private feedbackService: FeedbackService
  ) {
  }

  ngOnInit() {

  }

  checkText(feedback: FeedbackCheck) {
    this.feedbackService.checkFeedback(feedback).subscribe(
      {
        next: (data: FeedbackCheck) => {
          this.feedbackCheck.text = data.text;
          console.log(data)
          this.check = true;
        }
      }
    )
  }

  checkFeedbackByAI() {

  }

  createFeedbackByAI() {

  }

  protected readonly AI = AI;
}
