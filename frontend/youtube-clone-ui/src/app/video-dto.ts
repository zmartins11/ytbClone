export interface VideoDto {
    id : string;
    sessionId : string;
    title : string ;
    description : string;
    tags : Array<string>;
    videoUrl : string;
    videoStatus : string;
    thumbnailUrl : string;
    likeCount: number;
    dislikeCount: number; 
    viewCount: number;
    userId : string;
    createdAt: string;
}