
var ctx;
var ftToPixelsScale = 14;
var fieldWidth = 74; //in feet
var fieldHeight = 30; //in feet
var width = fieldWidth * ftToPixelsScale; //in pixels
var height = fieldHeight * ftToPixelsScale; //in pixels
var robotWidth = 40/12; //in feet
var robotHeight = 35/12; //in feet
var pointRadius = 5; //in pixels
var kEpsilon = 1E-9; 
var image;
var imageFlipped;
var waypoints = [];
var isFlipped = false;
var startXY = [64, 16];
var startX;
var startY;

class Point {
	constructor(x, y) {
		this.x = x;
		this.y = y;
		/*this.vel = vel;
		this.radius = radius;
		this.desc = desc;*/
	}

	norm() {
		return Math.sqrt(Point.dot(this, this));
	}

	scale(s) {
		return new Point(this.x * s, this.y * s);
	}

	translate(t) {
		return new Point(this.x + t.x, this.y + t.y);
	}

	invert() {
		return new Point(-this.x, -this.y);
	}

	perp() {
		return new Point(-this.y, this.x);
	}

    getX() {
		return this.x*ftToPixelsScale;
	}

	getY() {
		return height - this.y*ftToPixelsScale;
	}

	getAngle() {
		return Math.atan2(-this.y, this.x);
	}

	draw() {
		var color = "#f72c1c";
		ctx.beginPath();
		ctx.arc(this.getX(), this.getY(), pointRadius, 0, 2*Math.PI, false);
		ctx.fillStyle = color;
		ctx.strokeStyle = color;
		ctx.fill();
		ctx.lineWidth = 0;
		ctx.stroke();
	}

	static diff(a, b) {
		return new Point(b.x - a.x, b.y - a.y);
	}

	static cross(a, b) {
		return a.x * b.y - a.y * b.x;
	}

	static dot(a, b) {
		return a.x * b.x + a.y * b.y;
	}

	static angle(a, b) {
		return Math.acos(Point.dot(a,b) / (a.norm() * b.norm()));
	}
}

class WayPoint {
    constructor(x, y, vel, radius, desc) {
        this.position = new Point(x, y);
        this.vel = vel;
        this.radius = radius;
        this.desc = desc;
    }

    draw() {
        this.position.draw((this.radius > 0) ? "rgba(120,120,120,0.8)" : null);
    }

    /*toString() {
    		return "sWaywaypoints.add(new Waypoint("+this.position.x+","+this.position.y+","+this.radius+","+this.velocity+"));";
    	}*/
}

class Line {
    constructor(pointA, pointB) {
        this.start = pointA.position;
		this.end = pointB.position;
		this.slope = Point.diff(pointA.position, pointB.position);
    }

    draw() {
		var color = "#2dc65b";
        ctx.beginPath();
        ctx.moveTo(this.start.getX(), this.start.getY());
        ctx.lineTo(this.end.getX(), this.end.getY());
        ctx.strokeStyle = color;
		ctx.lineWidth = 2;
        ctx.stroke();
    }

    static intersect(a, b, c, d) {
        lineA = new Line(a, b);
        lineC = new Line(c, d);
        var slopeA = lineA.slope;
        var slopeC = lineC.slope;
        var y_intA = -slopeA(a.x) + a.y;
        var y_intC = -slopeC(c.x) + c.y;
        var x = (y-intC - y-intA)/(slopeA - slopeC);
        var y = (slopeA * x) - y-intA;
        return new Point(x,y);
    }
}

class Arc {

        constructor (lineA, lineB) {
            this.lineA = lineA;
            this.lineB = lineB;
            this.center = Line.intersect(lineA.end, lineA.end.translate(lineA.slope.perp()), lineB.start, lineB.start.translate(lineB.slope.perp()));
            //this.center.draw;
            this.radius = Point.diff(lineA.end, this.center).norm();
        }

        draw() {
            var sTrans = Point.diff(this.center, this.lineA.end);
            var eTrans = Point.diff(this.center, this.lineB.start);
            var sAngle, eAngle;

            if(Point.cross(sTrans, eTrans) > 0) {
            	eAngle = -Math.atan2(sTrans.y, sTrans.x);
            	sAngle = -Math.atan2(eTrans.y, eTrans.x);
            } else {
            	sAngle = -Math.atan2(sTrans.y, sTrans.x);
            	eAngle = -Math.atan2(eTrans.y, eTrans.x);
            }

            this.lineA.draw;
            this.lineB.draw;
            ctx.beginPath;
            ctx.arc(this.center.drawX,this.center.drawY,this.radius*ftToPixelsScale,sAngle,eAngle);
            ctx.strokeStyle=getColorForSpeed(this.lineB.pointB.speed);
            ctx.stroke();
        }


        fill() {
            this.lineA.fill();
            this.lineB.fill();
            var sTrans = Point.diff(this.center, this.lineA.end);
            var eTrans = Point.diff(this.center, this.lineB.start);
            var sAngle = (Point.cross(sTrans, eTrans) > 0) ? sTrans.angle : eTrans.angle;
            var angle = Point.angle(sTrans, eTrans);
            var length = angle * this.radius;
            for(var i=0; i<length; i+=this.radius/100) {
                //drawRotatedRect(this.center.translate(new Point(this.radius*Math.cos(sAngle-i/length*angle),-this.radius*Math.sin(sAngle-i/length*angle))), robotHeight, robotWidth, sAngle-i/length*angle+Math.PI/2, null, pathFillColor, true);
            }
        }

        static fromwaypoints(a, b, c) {
            return new Arc( new Line(a, b), new Line(b, c));
        }
}

function deletePoint(index) {
    $('tr')[index].remove();
}

function addPoint(cx, cy) {
    prevPoint = waypoints[waypoints.length-1].position;
	$('tbody').append('<tr>'
		+'<td class="hoverable"><input class="x number_cell" value="'+(cx)+'"></td>'
		+'<td class="hoverable"><input class="y number_cell" value="'+(cy)+'"></td>'
		+'<td class="hoverable"><input class="vel number_cell" value="10"></td>'
		+'<td class="hoverable"><input class="radius number_cell" value="0"></td>'
		+'<td class="hoverable"><input class="desc" placeholder="Description"></td>'
		+'<td><button class="delete">Delete</button></td>'
		+'</tr>'
		);
	addEventListeners();
    update();
}
function invertField() {
    if (!isFlipped) {
        $('#field').addClass('invert_field');
        isFlipped = true;
    }
    else{
        $('#field').removeClass('invert_field');
        isFlipped = false;
    }
}

function centerStart() {
    if(isFlipped){
        startXY = [64.0, 16.0]
    }else {
        startXY = [10.0, 14.0];
    }
    startX = startXY[0];
    startY = startXY[1];
    updateStartPoint();
}

function leftStart() {
    if(isFlipped){
        startXY = [64.0, 6.0]
    }else {
        startXY = [10.0, 24.0];
    }
    startX = startXY[0];
    startY = startXY[1];
    updateStartPoint();
}

function rightStart() {
    if(isFlipped){
        startXY = [64.0, 24.0]
    }else {
        startXY = [10.0, 6.0];
    }
    startX = startXY[0];
    startY = startXY[1];
    updateStartPoint();
}

function updateStartPoint() {
    waypoints.push(new WayPoint(startX, startY, 0, 0, ""));
    $($('tbody').children('tr')[0]).find('.x').val(startX);
    $($('tbody').children('tr')[0]).find('.y').val(startY);
    update();
}

function fitSizeToText(input) {
    var maxWidth = input.width() - 2;
    var val = input.val();
    if (val == 0) {return};
    $('#text_width_calc').text(val);
    var textWidth = $('#text_width_calc').width();
    var width = textWidth < maxWidth ? textWidth : maxWidth;
    input.css('width', width);
}

function addEventListeners() {

    row = $($('tbody').children('tr')[waypoints.length]);

    row.find('td').keyup(function() {
        update();
    });

    row.find('.desc').focus(function() {
        $(this).css('width','100%');
    }).focusout(function() {
        fitSizeToText($(this));
    });

    row.find('.delete').click(function() {
        $(this).parent().parent().remove();
        update();
    });

    row.find('.hoverable').hover(
        function() {
            $(this).find('.hoverable').css("background-color", "#f5f5f5");
        },
        function() {
            $(this).find('.hoverable').css("background-color", "white");
        }
    )
}

function update() {
    ctx.clearRect(0, 0, width, height);
    ctx.drawImage(image, 0, 0, width, height);
    waypoints = [];
    $('tbody').children('tr').each(function() {
        var row = $(this);
        var x = $(row.find('.x')).val();
        var y = $(row.find('.y')).val();
        var vel = $(row.find('.vel')).val();
        var desc = $(row.find('.desc')).val();
        var radius = $(row.find('.radius')).val();
        waypoints.push(new WayPoint(x, y, vel, radius, desc));
    })
    for (var point in waypoints) {
        waypoints[point].position.draw();
        if (point > 0) {
            if(waypoints[point].position.radius == 0){
                var line = new Line(waypoints[point-1], waypoints[point]);
                line.draw();
            } else {
            
            }
        }
    }
}

function onDown(event){
        cx = Math.round((event.offsetX/ftToPixelsScale)*10)/10;
        cy = Math.round((30-event.offsetY/ftToPixelsScale)*10)/10;
        addPoint(cx, cy);
    }

function init() {
	$('#field').css("width", width);
	ctx = document.getElementById('field').getContext('2d');
    ctx.canvas.width = width;
    ctx.canvas.height = height;
    ctx.clearRect(0, 0, width, height);
    ctx.fillStyle="#FF0000";
    image = new Image();
    image.src = 'PowerUpField.PNG';
    image.onload = function(){
        ctx.drawImage(image, 0, 0, width, height);
        var field = document.getElementById('field');
        var context = field.getContext('2d');
        field.addEventListener('mousedown',onDown,false);
        update();
    }
	addEventListeners();
}