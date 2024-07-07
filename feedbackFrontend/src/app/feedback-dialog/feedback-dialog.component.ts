import {Component, Inject} from '@angular/core';
import {FeedbackData} from "../model/FeedbackData";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {MatCheckbox} from "@angular/material/checkbox";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-feedback-dialog',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatFormField,
    MatInput,
    MatDialogActions,
    MatButton,
    MatCheckbox,
    FormsModule,
    MatLabel
  ],
  templateUrl: './feedback-dialog.component.html',
  styleUrl: './feedback-dialog.component.css'
})
export class FeedbackDialogComponent {

  feedbackData: FeedbackData = new FeedbackData();

  constructor(
    public dialogRef: MatDialogRef<FeedbackDialogComponent>) {
  }



  onNoClick(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    this.dialogRef.close(this.feedbackData);
  }
}
