import {Position} from "./position";
import {TotalStands} from "./totalStands";

export interface Station {
  positionDto: Position;
  totalStandsDto: TotalStands
}
