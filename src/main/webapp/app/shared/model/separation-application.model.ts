import { Moment } from 'moment';
import { IEmployee } from 'app/shared/model//employee.model';
import { ILineItem } from 'app/shared/model//line-item.model';

export interface ISeparationApplication {
    id?: number;
    dateOfLeaving?: Moment;
    dateOfSubmission?: Moment;
    reasonForLeaving?: string;
    completed?: boolean;
    employee?: IEmployee;
    lineItems?: ILineItem[];
    functionalRepresentatives?: IEmployee[];
}

export class SeparationApplication implements ISeparationApplication {
    constructor(
        public id?: number,
        public dateOfLeaving?: Moment,
        public dateOfSubmission?: Moment,
        public reasonForLeaving?: string,
        public completed?: boolean,
        public employee?: IEmployee,
        public lineItems?: ILineItem[],
        public functionalRepresentatives?: IEmployee[]
    ) {
        this.completed = false;
    }
}
