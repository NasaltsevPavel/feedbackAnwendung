import {FeedbackCheck} from "../model/FeedbackCheck";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class FeedbackService{
  private pathPrefix = "http://localhost:8080/feedback/";

  constructor(private http: HttpClient) {
  }

  public checkFeedback(feedback: FeedbackCheck): Observable<FeedbackCheck>{
    return this.http.post<FeedbackCheck>(this.pathPrefix+"check", feedback);

  }
}
