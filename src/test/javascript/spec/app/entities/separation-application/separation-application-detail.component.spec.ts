/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ManageMeTestModule } from '../../../test.module';
import { SeparationApplicationDetailComponent } from 'app/entities/separation-application/separation-application-detail.component';
import { SeparationApplication } from 'app/shared/model/separation-application.model';

describe('Component Tests', () => {
    describe('SeparationApplication Management Detail Component', () => {
        let comp: SeparationApplicationDetailComponent;
        let fixture: ComponentFixture<SeparationApplicationDetailComponent>;
        const route = ({ data: of({ separationApplication: new SeparationApplication(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManageMeTestModule],
                declarations: [SeparationApplicationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SeparationApplicationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SeparationApplicationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.separationApplication).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
