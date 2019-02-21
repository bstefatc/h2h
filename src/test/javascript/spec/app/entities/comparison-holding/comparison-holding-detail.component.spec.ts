/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { H2HTestModule } from '../../../test.module';
import { ComparisonHoldingDetailComponent } from 'app/entities/comparison-holding/comparison-holding-detail.component';
import { ComparisonHolding } from 'app/shared/model/comparison-holding.model';

describe('Component Tests', () => {
    describe('ComparisonHolding Management Detail Component', () => {
        let comp: ComparisonHoldingDetailComponent;
        let fixture: ComponentFixture<ComparisonHoldingDetailComponent>;
        const route = ({ data: of({ comparisonHolding: new ComparisonHolding(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [H2HTestModule],
                declarations: [ComparisonHoldingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComparisonHoldingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComparisonHoldingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.comparisonHolding).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
