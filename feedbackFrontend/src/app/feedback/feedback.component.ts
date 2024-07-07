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
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {FeedbackData} from "../model/FeedbackData";
import {FeedbackDialogComponent} from "../feedback-dialog/feedback-dialog.component";

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
    MatError,
    MatProgressSpinner
  ],
  templateUrl: './feedback.component.html',
  styleUrl: './feedback.component.css'
})
export class FeedbackComponent implements OnInit {

  roleOptions = Role.getRoleOptions();
  aiOptions = AI.getAIOptions();
  aiOption: string = '';
  check!: boolean;
  isLoading: boolean = false;

  feedbackCheck: FeedbackCheck = new FeedbackCheck();

  feedback: Feedback = new Feedback();

  feedbackData: FeedbackData = new FeedbackData();

  constructor(
    private feedbackService: FeedbackService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.check = false;

  }

  checkText(feedback: FeedbackCheck) {
    this.isLoading = true;
      this.feedbackService.checkFeedback(feedback).subscribe({
        next: (data: FeedbackCheck) => {
          this.feedbackCheck.text = data.text;
          this.check = true;
          this.showSnackBar('Feedback checked successfully!');
        },
        complete: () => {
          this.isLoading = false;
        },
        error: (err) => {
          this.isLoading = false;
          this.showSnackBar('Failed to check feedback: ' + err.message);
        }
      });
  }
  private showSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }
  createFeedback(feedback: FeedbackCheck){
    const dialogRef = this.dialog.open(FeedbackDialogComponent, {
      width: '250px',
      data: this.feedbackData
    });

    dialogRef.afterClosed().subscribe(result => {

      if (result) {
        console.log(result)
        this.feedback.text =  this.feedbackCheck.text;
        this.feedback.anonym = result.anonym;
        this.feedback.meeting = result.meeting;
        this.feedback.sender = this.feedbackCheck.from;
        this.feedback.receiver = this.feedbackCheck.to;
        this.feedback.active = true;
        console.log('The dialog was closed with the following data:', this.feedback);

      }
    });

  }

  checkFeedbackByAI() {

  }

  createFeedbackByAI() {

  }

  protected readonly AI = AI;
}
