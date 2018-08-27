import { Component, OnInit } from '@angular/core';
import { UrlService } from '../url.service';
import { Url } from '../Url';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
 

export class DashboardComponent implements OnInit {
  urls: Url[] = [];
  message: string = null;
  constructor(private urlService: UrlService) { }
  ngOnInit() {
    this.getUrls();
  }
  
  getUrls(): void{
    this.urlService.getUrls().subscribe(urls => this.urls = urls);
  }

  addShortUrl(urlString: string): Url{
    if(!urlString){
      this.message = "Url cannot be Empty";
      return null;
    }else{
      this.urlService.addShortUrl(urlString).subscribe(url => this.urls.push(url));
      this.message = "Url added Successfully";
      location.reload();
    }
  }
}
