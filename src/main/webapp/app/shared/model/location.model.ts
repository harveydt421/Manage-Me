export interface ILocation {
    id?: number;
    streetAddress?: string;
    postalCode?: number;
    city?: string;
    state?: string;
    country?: string;
}

export class Location implements ILocation {
    constructor(
        public id?: number,
        public streetAddress?: string,
        public postalCode?: number,
        public city?: string,
        public state?: string,
        public country?: string
    ) {}
}
