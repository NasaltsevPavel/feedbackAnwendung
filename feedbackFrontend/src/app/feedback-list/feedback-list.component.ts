import {Component} from '@angular/core';
import {FeedbackService} from "../service/feedback.service";
import {Feedback} from "../model/Feedback";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {MatCard, MatCardContent} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {Role} from "../model/Role.enum";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatIcon} from "@angular/material/icon";
import {FormsModule} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-feedback-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    MatCard,
    MatCardContent,
    MatFormField,
    MatSelect,
    MatOption,
    MatLabel,
    MatCheckbox,
    MatIcon,
    FormsModule,
    NgClass
  ],
  templateUrl: './feedback-list.component.html',
  styleUrl: './feedback-list.component.css'
})
export class FeedbackListComponent {
  feedbacks: Feedback[] = [];

  filters: any[] = [];
  selectedFilter: any;

  constructor(private feedbackService: FeedbackService,
              private snackBar: MatSnackBar,) {
  }

  ngOnInit(): void {
    this.filters = [["Alle", "Alle"], ...Role.getRoleOptionsFrom()];
    this.selectedFilter = this.filters[0].value;
    this.getAllFeedback();
  }



  getAllFeedback(): void {
    this.feedbackService.getAllFeedback().subscribe({
      next: (data: Feedback[]) => {
        this.feedbacks = data;
      }
    })
  }

  deleteFeedback(id: string){
    this.feedbackService.deleteFeedback(id).subscribe({
      next: () => {
        this.showSnackBar('Deleted');
        this.ngOnInit();
      },
      error: (err) => {
        this.showSnackBar('Failed to delete ' + err.message);
      }
    })
  }

  roleToString(role: string) {
    switch (role) {
      case role = Role.MANAGER:
        return 'Manager';
      case role = Role.DEVELOPER:
        return 'Software-Entwickler';
      case role = Role.PRODUCT_OWNER:
        return 'Product Owner';
      case role = Role.SCRUM_MASTER:
        return 'Scrum Master';
      case role = Role.WORKING_STUDENT:
        return 'Werkstudent';
      case role = Role.TRAINEE:
        return 'Praktikant';
      case role = Role.OFFICE:
        return 'Büro';
      case role = Role.COMPANY:
        return 'Unternehmen';
      default:
          return "Anonym"
    }
  }

  get filteredFeedbacks() {
    if (this.selectedFilter === 'Alle' || !this.selectedFilter) {
      return this.feedbacks;
    }
    return this.feedbacks.filter(feedback => feedback.receiver === this.selectedFilter);
  }

  changeStatus(id: string){
    this.feedbackService.changeStatus(id).subscribe({
      next: () => {
        this.showSnackBar('Changed');
      },
      error: (err) => {
        this.showSnackBar('Failed to change ' + err.message);
      }
    })
  }

  private showSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }
}
