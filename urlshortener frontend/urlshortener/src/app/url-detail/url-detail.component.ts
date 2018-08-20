import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Statistics } from '../stats';
import { UrlService } from '../url.service';
import { Chart } from 'chart.js';




@Component({
  selector: 'app-url-detail',
  templateUrl: './url-detail.component.html',
  styleUrls: ['./url-detail.component.css']
})
export class URLDetailComponent implements OnInit {
  stats: Statistics;
  clickCount: number = 0;
  constructor(
    private route: ActivatedRoute,
    private urlService: UrlService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getStats();
  }
  private statss: Statistics;
  clicksChart = [];
  platformsChart = [];
  browsersChart = [];
  getStats(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.urlService.getStats(id)
      .subscribe(stats => {
        this.stats = stats;
        let clicksLabels = this.stats.stats.clicks.labels;
        let clicksData = this.stats.stats.clicks.data;
        let platformLabels = this.stats.stats.platforms.labels;
        let platformData = this.stats.stats.platforms.data;
        let browsersLabels = this.stats.stats.browsers.labels;
        let browsersData = this.stats.stats.browsers.data;
        for (let entry of clicksData) {
          this.clickCount = this.clickCount + entry;
          console.log(entry);
        }
        this.clicksChart = this.lineChart(clicksLabels, clicksData);
        this.platformsChart = this.barChart(platformLabels, platformData);
        this.browsersChart = this.pieChart(browsersLabels, browsersData);
      });
  }


  lineChart(labels: String[], data: number[]): Chart {
    return new Chart('canvasClicks', {
      type: 'line',
      responsive: true,
      maintainAspectRatio: false,
      data: {
        labels: labels,
        datasets: [{
          data: data,
          label: "clicks",
          backgroundColor: ['#ED402A', '#F0AB05', '#A0B421', '#00A39F', '#00A37F'],

        }]
      },
      options: {
        title: {
          display: true,
          text: "Clicks Chart"
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }]
        }
      }
    })
  }
  barChart(labels: String[], data: number[]): Chart {
    return new Chart('canvasPlatforms', {
      type: 'horizontalBar',
      responsive: true,
      maintainAspectRatio: false,
      data: {
        labels: labels,
        datasets: [{
          data: data,
          label: "platforms",
          backgroundColor: ['#ED402A', '#F0AB05', '#A0B421', '#00A39F', '#00A37F', 'yellow', 'pink', 'blue']
        }]
      },
      options: {
        legend: {
          display: true,
          backgroundColor: "#ED402A",
          borderColor: "#000"
        },
        title: {
          display: true,
          text: "Platforms Chart"
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }]
        }
      }
    })
  }
  pieChart(Labels: String[], Data: number[]): Chart {
    return new Chart('canvasBrowsers', {
      type: "pie",
      data: {
        labels: Labels,
        datasets: [{
          data: Data,
          backgroundColor: ['#ED402A', '#F0AB05', '#A0B421', '#00A39F', '#00A37F'],
          borderColor: ['#ED402A', '#F0AB05', '#A0B421', '#00A39F', '#00A37F']
        }]
      },
      options: {
        title: {
          display: true,
          text: "Browsers"
        }
      }
    })
  }
 reloadPage(): void{
    location.reload();
  }
}
