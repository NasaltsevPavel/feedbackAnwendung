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
import {AiInteraction} from "../model/AiInteraction";
import {Feedback} from "../model/Feedback";
import {FeedbackService} from "../service/feedback.service";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {FeedbackData} from "../model/FeedbackData";
import {FeedbackDialogComponent} from "../feedback-dialog/feedback-dialog.component";
import {Router} from "@angular/router";

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

  roleOptionsFrom = Role.getRoleOptionsFrom();
  roleOptionsTo = Role.getRoleOptionsTo();
  aiOptions = AI.getAIOptions();
  aiOption: string = '';
  check!: boolean;
  create!: boolean;
  isLoading: boolean = false;

  aiInteraction: AiInteraction = new AiInteraction();

  feedback: Feedback = new Feedback();

  feedbackData: FeedbackData = new FeedbackData();

  constructor(
    private feedbackService: FeedbackService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.check = false;
    this.create = false;
  }

  createText(feedback: AiInteraction){
    this.isLoading = true;
    this.feedbackService.aiCreateFeedback(feedback).subscribe({
      next: (data: AiInteraction) => {
        this.aiInteraction.feedback = data.feedback;
        this.aiInteraction.aiGenerated = data.aiGenerated;
        this.create = true;
        this.showSnackBar('Feedback created successfully!');
      },
      complete: () => {
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        this.showSnackBar('Failed to create feedback: ' + err.message);
      }
    });
  }
  checkText(feedback: AiInteraction) {
    this.isLoading = true;
      this.feedbackService.aiCheckFeedback(feedback).subscribe({
        next: (data: AiInteraction) => {
          this.aiInteraction.feedback = data.feedback;
          this.aiInteraction.aiGenerated = data.aiGenerated;
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
  createFeedback(aiInteraction: AiInteraction){
    const dialogRef = this.dialog.open(FeedbackDialogComponent, {
      width: '520px',
      data: this.feedbackData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if(result.anonym){
          this.feedback.sender = Role.ANONYM;
        }
        this.feedback.text = aiInteraction.feedback;
        this.feedback.anonym = result.anonym;
        this.feedback.meeting = result.meeting;
        this.feedback.active = true;
        this.feedbackService.createFeedback(this.feedback).subscribe({
          next: () => {
            this.showSnackBar('Feedback created successfully!');
            this.router.navigate(['/feedback-list']);
          },
          error: (err) => {
            this.showSnackBar('Failed to create feedback: ' + err.message);
          }
        })
        console.log('FEEDBACK:', this.feedback);

      }
    });

  }

  protected readonly AI = AI;
}
