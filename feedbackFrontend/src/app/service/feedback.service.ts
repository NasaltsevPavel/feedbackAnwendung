import {AiInteraction} from "../model/AiInteraction";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Feedback} from "../model/Feedback";

@Injectable({
  providedIn: 'root',
})
export class FeedbackService{
  private pathPrefix = "http://localhost:8080/";

  constructor(private http: HttpClient) {
  }

  public checkFeedback(feedback: AiInteraction): Observable<AiInteraction>{
    return this.http.post<AiInteraction>(this.pathPrefix+"ai/check", feedback);

  }

  public createFeedback(feedback: Feedback): Observable<Feedback>{
    return this.http.post<Feedback>(this.pathPrefix+"feedback/create", feedback);

  }

  public getAllFeedback(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.pathPrefix + 'feedback/feedbacks');
  }
}
