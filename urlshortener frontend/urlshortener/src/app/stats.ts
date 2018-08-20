import { Url } from "./Url";

export class Statistics {
    constructor(
      public urlInfo: Url,
      public stats : Graph
          ) {  }
      }

interface Clicks{
     labels: String[];
     data: number[];
}

interface Browsers{
    labels: String[];
    data: number[];
}

interface Platforms{
    labels: String[];
    data: number[];
}
interface Graph{
    clicks : Clicks,
    browsers: Browsers,
    platforms: Platforms
}