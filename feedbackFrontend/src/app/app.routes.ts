import {Routes} from '@angular/router';
import {MainPageComponent} from "./main-page/main-page.component";
import {FeedbackComponent} from "./feedback/feedback.component";
import {FeedbackListComponent} from "./feedback-list/feedback-list.component";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'main-page',
    pathMatch: 'prefix'
  },
  {
    path: 'main-page',
    component: MainPageComponent
  },
  {
    path: 'feedback',
    component: FeedbackComponent
  },
  {
    path: 'feedback-list',
    component: FeedbackListComponent
  },
];
