export class Url {
    public id: number;
    public originalUrl: string;
    public createdOn: Date;
    public shortUrl: string;
    public totalClicksCount: number;
    constructor() { }
    getId(): number {
        return this.id;
    }

    getOriginalUrl(): string {
        return this.originalUrl;
    }
    getCreatedOn(): Date {
        return this.createdOn;
    }
    getShortUrl(): string {
        return this.shortUrl;
    }
    getTotalClicksCount(): number {
        return this.totalClicksCount;
    }
    setId(id: number): void {
        this.id = id;
    }

    setOriginalUrl(originalUrl: string): void {
        this.originalUrl = originalUrl;
    }
    setCreatedOn(createdOn: Date): void {
        this.createdOn = createdOn;
    }
    setShortUrl(shortUrl: string): void {
        this.shortUrl = shortUrl;
    }
    setTotalClicksCount(totalClicksCount: number): void {
        this.totalClicksCount = totalClicksCount;
    }
}