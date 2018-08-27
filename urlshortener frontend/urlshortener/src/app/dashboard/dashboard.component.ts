import { Component, OnInit } from '@angular/core';
import { UrlService } from '../url.service';
import { Url } from '../Url';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})


export class DashboardComponent implements OnInit {
  urls: Url[] = [];
  message: string = null;
  constructor(private urlService: UrlService) { }
  ngOnInit() {
    this.getUrls();
  }

  getUrls(): void {
    this.urlService.getUrls().subscribe(urls => this.urls = urls);
  }

  addShortUrl(urlString: string): void {
    if (!urlString) {
      this.message = "Url cannot be Empty";
    } else {
      if ((urlString.includes("http://") || (urlString.includes("https://"))) && urlString.includes(".com")) {
        this.urlService.addShortUrl(urlString).subscribe(url => this.urls.push(url));
        this.message = "Url added Successfully";
        location.reload();
        console.log("else if");
      }
      else {
        this.message = "invalid Url";
      }
    }
  }
  filterBy(prop:  string)  {
      return  this.urls.sort((a,  b)  =>  a[prop]  >  b[prop]  ?  1  :  a[prop]  ===  b[prop]  ?  0  :  -1);
  }
}