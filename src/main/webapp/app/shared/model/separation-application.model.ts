import { Moment } from 'moment';
import { ILineItem } from 'app/shared/model//line-item.model';
import { IEmployee } from 'app/shared/model//employee.model';

export interface ISeparationApplication {
    id?: number;
    dateOfLeaving?: Moment;
    dateOfSubmission?: Moment;
    reasonForLeaving?: string;
    completed?: boolean;
    employeeId?: number;
    employeeName?: string;
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
        public employeeId?: number,
        public lineItems?: ILineItem[],
        public functionalRepresentatives?: IEmployee[],
        public empoyeeName?: string
    ) {
        this.completed = false;
    }
}
