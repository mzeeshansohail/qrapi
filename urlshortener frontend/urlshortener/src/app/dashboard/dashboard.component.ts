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
  constructor(private urlService: UrlService) { }
  ngOnInit() {
    this.getUrls();
  }

  getUrls(): void {
    this.urlService.getUrls().subscribe(urls => this.urls = urls);
  }

  addShortUrl(url: string): void {
    if (!url) {

    } else {
      this.urlService.addShortUrl(url).subscribe(url => this.urls.push(url));
      location.reload();
    }
  }
}
