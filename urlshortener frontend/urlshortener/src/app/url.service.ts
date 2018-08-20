import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Url } from './Url';
import { Statistics } from './stats';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class UrlService {

  private apiUrl = 'http://localhost:8080/urlshortener/';  // URL to web api

  constructor(
    private http: HttpClient) { }

  /** GET Urls from the server */
  getUrls (): Observable<Url[]> {
    return this.http.get<Url[]>(this.apiUrl+'all')
      .pipe(
        tap(Urls => this.log('fetched Urls')),
        catchError(this.handleError('getUrls', []))
      );
  }

  getStats(id: number): Observable<Statistics>{
    return this.http.get<Statistics>(this.apiUrl+'stats/'+id).pipe(tap(Stats=> this.log('fetched Stats')));
  }

  addShortUrl(longUrl : string): Observable<Url>{
    let url : Url = new Url();
    url.setOriginalUrl(longUrl);
    longUrl = JSON.stringify(url);
    console.log(longUrl);
    return this.http.post<Url>(this.apiUrl,longUrl , httpOptions);
  }



  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(`UrlService: ${message}`);
  }
}
