export type Review = {
    id: number;
    text: string;
    userId: string;
}

export type Movie = {
    id: number;
    title: string;
    subtitle: string;
    year: number;
    imgUrl: string;
    synopsis: string;
    reviews: Review[];
    genreId: number;
}

export type ContentResponse = {
    content: Movie[];
    totalPages: number;
}

export type Genre = {
    id: number;
    name: string;
}