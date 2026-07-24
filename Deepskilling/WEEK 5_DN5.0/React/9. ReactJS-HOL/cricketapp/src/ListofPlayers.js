import React from "react";

export function ListofPlayers({ players }) {
    return (
        <div>
            <ul>
                {players.map((item, index) => (
                    <li key={index}>
                        Mr. {item.name} <span>{item.score}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export function Scorebelow70({ players }) {
    const players70 = players.filter(player => player.score < 70);

    return (
        <div>
            <ul>
                {players70.map((item, index) => (
                    <li key={index}>
                        Mr. {item.name} <span>{item.score}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
}